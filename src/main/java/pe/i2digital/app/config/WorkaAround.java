package pe.i2digital.app.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.stereotype.Component;
import springfox.documentation.oas.web.OpenApiTransformationContext;
import springfox.documentation.oas.web.WebMvcOpenApiTransformationFilter;
import springfox.documentation.spi.DocumentationType;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Component
public class WorkaAround implements WebMvcOpenApiTransformationFilter {

    @Override
    public OpenAPI transform(OpenApiTransformationContext<HttpServletRequest> context) {
        OpenAPI openApi = context.getSpecification();
        openApi.setInfo(getInfo());
        Server localServer = new Server();
        localServer.setDescription("local");
        localServer.setUrl("http://localhost:8085/");

        Server testServer = new Server();
        testServer.setDescription("test");
        testServer.setUrl("https://qa-appat.i2digital.edu.pe/");

        Server production = new Server();
        production.setDescription("production");
        production.setUrl("https://appat.i2digital.edu.pe/");
        openApi.setServers(Arrays.asList(localServer, testServer, production));
        return openApi;
    }

    @Override
    public boolean supports(DocumentationType documentationType) {
        return documentationType.equals(DocumentationType.OAS_30);
    }

    private Info getInfo() {
        var info = new Info();
        info.setTitle("Documentación del REST Api con Openapi OAS3");
        info.setDescription("Documentacion del API REST de los recursos del backend de la plataforma AT");
        info.setVersion("1.0.0");
        var contact = new Contact();
        contact.setName("Omar Saavedra Salazar");
        contact.setEmail("osaavedras@unprg.edu.pe");
        contact.setUrl("https://i2digital.pe/");
        info.setContact(contact);
        var license = new License();
        license.setName("Apache 2.0");
        license.setUrl("http://www.apache.org/licenses/LICENSE-2.0");
        info.setLicense(license);
        info.setTermsOfService("urn:tos");
        return info;
    }
}
