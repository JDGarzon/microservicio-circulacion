package co.analisys.biblioteca.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import co.analisys.biblioteca.bean.FeignRequestConfig;
import co.analisys.biblioteca.dto.NotificacionDTO;
@FeignClient(name = "notificacion-service", url = "http://localhost:8084",configuration = FeignRequestConfig.class)
public interface NotificacionClient {
 @PostMapping("/notificar")
 void enviarNotificacion(@RequestBody NotificacionDTO notificacion);
}

