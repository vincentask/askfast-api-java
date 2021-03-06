
package com.askfast.askfastapi.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;

import com.askfast.askfastapi.model.EventPost.EventType;
import com.askfast.askfastapi.model.MediaProperty.MediaPropertyKey;
import com.askfast.askfastapi.model.MediaProperty.MediumType;
import com.askfast.model.ModelBase;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Question extends ModelBase{
	
	public static final String QUESTION_TYPE_CLOSED = "closed";
	public static final String QUESTION_TYPE_OPEN = "open";
	public static final String QUESTION_TYPE_COMMENT = "comment";
	public static final String QUESTION_TYPE_REFERRAL = "referral";
	public static final String QUESTION_TYPE_VOICE_RECORDING = "audio";
	
	private Collection<MediaProperty> media_properties;
	private String preferred_language = "nl";
	private String question_id = "";
	private String question_text = null;
	private String type = null;
	private String url = null;
	
	ArrayList<Answer> answers;
	ArrayList<EventCallback> event_callbacks;
	
	public Question() {
		this(UUID.randomUUID().toString(), "", "");
	}
	
	public Question(String id, String text, String type) {
		this.setQuestion_id(id);
		this.setQuestion_text(text);
		this.setType(type);
		
		this.answers = new ArrayList<Answer>();
		this.event_callbacks = new ArrayList<EventCallback>();
	}
	
	public void addAnswer(Answer answer) {
		this.answers.add(answer);
	}

	// Getters/Setters:
	public String getQuestion_id() {
		return this.question_id;
	}

	public String getQuestion_text() {
		return this.question_text;
	}

	public String getType() {
		return this.type;
	}

	public String getUrl() {
		return this.url;
	}

	public ArrayList<Answer> getAnswers() {
		return this.answers;
	}

	public ArrayList<EventCallback> getEvent_callbacks() {
		return this.event_callbacks;
	}

	public void setQuestion_id(String question_id) {
		this.question_id = question_id;
	}

	public void setQuestion_text(String question_text) {
		this.question_text = question_text;
	}

	public void setType(String type) {
		this.type = type;
	}
	
    public String getPreferred_language()
    {
        return preferred_language;
    }

    public void setPreferred_language( String preferred_language )
    {
        this.preferred_language = preferred_language;
    }

    public void setUrl( String url )
    {
        if ( !url.startsWith( "http" ) && !url.startsWith( "tel:" ) )
        {
            url = "tel:" + url;
        }
        this.url = url;
    }
	
	public void setAnswers(ArrayList<Answer> answers) {
		this.answers = answers;
	}

	public void setEvent_callbacks(ArrayList<EventCallback> event_callbacks) {
		this.event_callbacks = event_callbacks;
	}
	
	public static Question fromJson(String json)
	{
	    return fromJSON( json, Question.class );
	}

	@JsonProperty("media_properties")
    public Collection<MediaProperty> getMediaProperties()
    {
        return media_properties;
    }

    @JsonProperty("media_properties")
    public void setMediaProperties( Collection<MediaProperty> media_Hints )
    {
        this.media_properties = media_Hints;
    }

    public void addMediaProperties( MediaProperty mediaProperty )
    {
        media_properties = media_properties == null ? new ArrayList<MediaProperty>() : media_properties;
        boolean propertyUpdated = false;
        for ( MediaProperty property : media_properties )
        {
            if(property.getMedium().equals( mediaProperty.getMedium() ))
            {
                property.getProperties().putAll( mediaProperty.getProperties() );
                propertyUpdated = true;
            }
        }
        if(!propertyUpdated)
        {
            media_properties.add( mediaProperty );
        }
    }
    
    @JsonIgnore
    public Map<MediaPropertyKey, String> getMediaPropertyByType( MediumType type )
    {

        if ( this.media_properties != null )
        {
            for ( MediaProperty mediaProperties : this.media_properties )
            {
                if ( mediaProperties.getMedium().equals( type ) )
                {
                    return mediaProperties.getProperties();
                }
            }
        }
        return null;
    }

    public String getMediaPropertyValue( MediumType type, MediaPropertyKey key )
    {

        Map<MediaPropertyKey, String> properties = getMediaPropertyByType( type );
        if ( properties != null )
        {
            if ( properties.containsKey( key ) )
            {
                return properties.get( key );
            }
        }
        return null;
    }

    public void addEvent_callbacks( EventType eventType, String callbackURL )
    {
        event_callbacks = event_callbacks != null ? event_callbacks : new ArrayList<EventCallback>();
        event_callbacks.add( new EventCallback( eventType, callbackURL ) );
    }

    public void addEventCallback( EventCallback eventCallback )
    {
        event_callbacks = event_callbacks != null ? event_callbacks : new ArrayList<EventCallback>();
        event_callbacks.add( eventCallback );
    }
}