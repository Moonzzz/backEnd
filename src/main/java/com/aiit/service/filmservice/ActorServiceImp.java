package com.aiit.service.filmservice;

import com.aiit.dao.filmdao.IActorDao;
import com.aiit.pojo.filmpojo.Actor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service(value="actorService")
public class ActorServiceImp implements IActorService {
    private final IActorDao actorDao;
    public ActorServiceImp(IActorDao actorDao){
        this.actorDao=actorDao;
    }

    @Override
    public List<Actor> showActor(int offset, int limit) {
        return actorDao.showActors(offset, limit);
    }

    @Override
    public int showCount() {
        return actorDao.showCount();
    }

    @Override
    public int delActor(int id) {
        return actorDao.delActor(id);
    }

    @Override
    public int addActor(Actor actor) {
        return actorDao.addActor(actor);
    }
}
