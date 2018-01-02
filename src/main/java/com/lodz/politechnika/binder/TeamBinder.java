package com.lodz.politechnika.binder;

import com.lodz.politechnika.model.Team;
import com.lodz.politechnika.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

@Component
public class TeamBinder extends PropertyEditorSupport {

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public String getAsText() {
        Object obj = getValue();
        if(obj == null) {
            return null;
        }
        if(obj instanceof Team) {
            return ((Team) obj).getTeamId().toString();
        }
        throw new IllegalArgumentException("Value must be a Team.");
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if(text == null || 0 == text.length()) {
            setValue(null);
        } else {
            setValue(teamRepository.findOne(Long.parseLong(text)));
        }
    }
}
