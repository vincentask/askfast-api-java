package com.askfast.model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.askfast.askfastapi.AskFast;
import com.askfast.askfastapi.model.Question;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DialogObject extends ModelBase
{
//    private static final Logger log = Logger.getLogger(DialogObject.class.getSimpleName());
    String dialog_id;
    String firstQuestionId;
    Map<String, Question> questions;
    
    public DialogObject()
    {
        dialog_id = DialogObject.class.getSimpleName() + ":" + UUID.randomUUID().toString();
    }
    
    public String getDialog_id()
    {
        return dialog_id;
    }
    
    public void setDialog_id( String dialog_id )
    {
        this.dialog_id = dialog_id;
    }
    
    @JsonProperty("firstQuestionId")
    public String getFirstQuestionId()
    {
        return firstQuestionId;
    }
    
    @JsonProperty("firstQuestionId")
    public void setFirstQuestionId( String firstQuestionId )
    {
        this.firstQuestionId = firstQuestionId;
    }
    
    @JsonIgnore
    public void setFirstQuestionId( AskFast askFast )
    {
        this.firstQuestionId = askFast.getQuestionId();
    }
    
    public Map<String, Question> getQuestions()
    {
        return questions;
    }
    
    public void addQuestion(Question question) throws Exception
    {
        if(question.getQuestion_id() == null || question.getQuestion_id().isEmpty())
        {
            throw new Exception( "Question ID is null or empty" );
        }
        questions = questions != null ? questions : new HashMap<String, Question>();
        questions.put( question.getQuestion_id(), question );
    }
    
    public void addQuestion(AskFast askFast) throws Exception
    {
        String question_json = askFast.render();
        Question question = Question.fromJson( question_json );
        addQuestion( question );
    }
    
    public static DialogObject fromJSON(String json_string)
    {
        return fromJSON( json_string, DialogObject.class );
    }
}


