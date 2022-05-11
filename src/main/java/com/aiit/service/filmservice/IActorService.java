package com.aiit.service.filmservice;

import com.aiit.pojo.filmpojo.Actor;

import java.util.List;

public interface IActorService {
    List<Actor> showActor(int offset, int limit);
    int showCount();
    int delActor(int id);
    int addActor(Actor actor);
}
