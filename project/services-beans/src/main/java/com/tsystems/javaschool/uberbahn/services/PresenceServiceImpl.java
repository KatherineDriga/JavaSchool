package com.tsystems.javaschool.uberbahn.services;

import com.tsystems.javaschool.uberbahn.entities.Presence;
import com.tsystems.javaschool.uberbahn.entities.Train;
import com.tsystems.javaschool.uberbahn.repositories.PresenceRepository;
import com.tsystems.javaschool.uberbahn.repositories.TrainRepository;
import com.tsystems.javaschool.uberbahn.services.errors.DatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.time.Instant;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class PresenceServiceImpl implements PresenceService {

    private final TrainRepository trainRepository;
    private final PresenceRepository presenceRepository;

    @Autowired
    public PresenceServiceImpl(TrainRepository trainRepository, PresenceRepository presenceRepository) {
        this.trainRepository = trainRepository;
        this.presenceRepository = presenceRepository;
    }

    @Override
    public void archive() {
        Collection<Train> trains = trainRepository.findNotArchived();
        trains.forEach(train -> {
            List<Presence> presences = train.getPresences();
            Instant arrivalInstant = presences.get(presences.size() - 1).getInstant();
            if (arrivalInstant.isBefore(Instant.now())) {
                train.setArchived(true);
                try {
                    trainRepository.save(train);
                    presenceRepository.deleteInBatch(presences);
                } catch (PersistenceException ex) {
                    throw new DatabaseException("Error occurred", ex);
                }
            }
        });

    }
}
