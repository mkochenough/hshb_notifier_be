package de.hackerspacebremen.domain;

import static de.hackerspacebremen.common.PropertyConstants.*;

import com.google.inject.Inject;
import com.google.inject.Provider;

import de.hackerspacebremen.data.api.PropertyDAO;
import de.hackerspacebremen.data.entities.Property;
import de.hackerspacebremen.domain.api.PropertyService;
import de.hackerspacebremen.domain.val.ValidationException;
import de.hackerspacebremen.exceptions.NotCompletelyConfigured;
import de.hackerspacebremen.valueobjects.PushProperties;

public class PropertyServiceImpl implements PropertyService{

	@Inject
	private PropertyDAO propertyDAO;
	
	@Inject 
	private Provider<PushProperties> pushProperties;
	
	@Override
	public String findValueByKey(final String key) throws ValidationException {
		Property property = propertyDAO.findByKey(key);
		if(property==null){
			property = new Property();
			property.setKey(key);
			property.setValue("DUMMY!!!");
			propertyDAO.persist(property);
			throw new NotCompletelyConfigured();
		}
		return property.getValue();
	}

	@Override
	public PushProperties fetchPushProperties() {
		final PushProperties properties = pushProperties.get();
		properties.setGcmEnabled(
				Boolean.valueOf(findProperty(GCM_ENABLED, "false").getValue()));
		properties.setApnsEnabled(
				Boolean.valueOf(findProperty(APNS_ENABLED, "false").getValue()));
		properties.setApnsEnabled(
				Boolean.valueOf(findProperty(MPNS_ENABLED, "false").getValue()));
		properties.setGcmApiKey(findProperty(GCM_KEY, "").getValue());
		properties.setMailEnabled(
				Boolean.valueOf(findProperty(MAIL_ENABLED, "false").getValue()));
		return properties;
	}
	
	private Property findProperty(final String key, final String defaultValue){
		Property property = propertyDAO.findByKey(key);
		if(property==null){
			property = new Property();
			property.setKey(key);
			property.setValue(defaultValue);
			property = propertyDAO.persist(property);
		}
		return property;
	}

	@Override
	public PushProperties savePushProperties(boolean gcmEnabled,
			boolean apnsEnabled, boolean mpnsEnabled, String gcmKey){
		
		this.saveProperty(GCM_ENABLED, String.valueOf(gcmEnabled));
		this.saveProperty(APNS_ENABLED, String.valueOf(apnsEnabled));
		this.saveProperty(MPNS_ENABLED, String.valueOf(mpnsEnabled));
		this.saveProperty(GCM_KEY, gcmKey);
		
		return this.fetchPushProperties();
	}
	
	private void saveProperty(final String key, final String value){
		final Property property = propertyDAO.findByKey(key);
		property.setValue(value);
		propertyDAO.persist(property);
	}

	
}
