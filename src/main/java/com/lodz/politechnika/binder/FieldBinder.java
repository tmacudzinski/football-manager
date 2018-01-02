package com.lodz.politechnika.binder;

import com.lodz.politechnika.model.Field;
import com.lodz.politechnika.repository.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

@Component
public class FieldBinder extends PropertyEditorSupport {

    @Autowired
    private FieldRepository fieldRepository;

    @Override
    public String getAsText() {
        Object obj = getValue();
        if(obj == null) {
            return null;
        }
        if(obj instanceof Field) {
            return ((Field) obj).getFieldId().toString();
        }
        throw new IllegalArgumentException("Value must be a Field.");
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if(text == null || 0 == text.length()) {
            setValue(null);
        } else {
            setValue(fieldRepository.findOne(Long.parseLong(text)));
        }
    }
}
