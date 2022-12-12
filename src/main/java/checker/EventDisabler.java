package checker;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.proyecto.app.entity.Evento;
import com.proyecto.app.repository.IEventoDao;
import com.proyecto.app.wrapper.EventoWrapper;

@Component
@EnableScheduling
public class EventDisabler  {

    @Autowired
    private IEventoDao event;

    private final long SECOND = 1000;
    private final long MINUTE = SECOND * 60;
    private final long HOUR = MINUTE * 60;

    @Scheduled(fixedDelay = MINUTE, initialDelay = SECOND)
    public void eventDisabler() {
        List<Evento> events = event.findAll();
        LocalDate today = LocalDate.now();

        if (events.size() > 0) {

            events.stream().forEach((e) -> e.setActivo(true && today.isBefore(e.getFechaEvento()) || false));
            events.stream().forEach((e) -> event.save(e));
            events.stream().forEach((e) -> EventoWrapper.entityToDto(e));

        }

    }

}
