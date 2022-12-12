package conta.micro.rest;

import conta.micro.to.ContaTO;
import conta.micro.to.NumeroTO;
import conta.micro.to.TransferenciaTO;
import conta.sistema.casouso.porta.PortaTransferencia;
import conta.sistema.dominio.modelo.NegocioException;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import javax.ws.rs.*;

import static conta.micro.infra.Spring.inject;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

// Adaptador Rest
@Tag(name = "Transferencia Bancaria Microservices", description = "Adptador Hexagonal usando microprofile")
@Path("/transferencia/")
@Singleton
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class TransferenciaController {

    private PortaTransferencia porta;

    @PostConstruct
    public void init() {
        // faz a mesma coisa do @Inject
        porta = inject(PortaTransferencia.class);
    }

    @Operation(description = "Pesquisa uma conta pelo seu id")
    @APIResponses({@APIResponse(responseCode = "200", description = "Processou com sucesso."),
            @APIResponse(responseCode = "400", description = "Caso ocorra erro de processamento.")}
    )
    @Path("getconta")
    @POST
    public ContaTO getConta(NumeroTO to) throws NegocioException {
        //JSON {"conta": 300}
        var c = porta.getConta(to.conta);
        if (c != null) {
            return new ContaTO(c.getSaldo(), c.getCorrentista());
        } else {
            return new ContaTO(null, null);
        }
    }

    @Operation(description = "Processa a transferencia entre contas")
    @APIResponses({@APIResponse(responseCode = "200", description = "Processou com sucesso."),
            @APIResponse(responseCode = "400", description = "Caso ocorra erro de processamento.")}
    )
    @Path("transferir")
    @PUT
    public void transferir(TransferenciaTO to) throws NegocioException {
        //JSON {"conta1": 11, "conta2": 22, "valor": 33}
        porta.transferir(to.conta1, to.conta2, to.valor);
    }
}
