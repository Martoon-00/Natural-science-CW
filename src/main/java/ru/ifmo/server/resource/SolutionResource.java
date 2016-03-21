package ru.ifmo.server.resource;

import com.sun.jersey.spi.resource.Singleton;
import ru.ifmo.data.Parameters;
import ru.ifmo.data.TSection;
import ru.ifmo.methods.ExtendedSolver;
import ru.ifmo.methods.RegisteredSolvers;
import ru.ifmo.server.ServerHelper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("science")
@Singleton
public class SolutionResource {

    @GET
    @Path("methods")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String enumerateMethods() {
        return RegisteredSolvers.solvers.keySet().stream()
                .collect(Collectors.joining("\n"));
    }

    @GET
    @Path("defparams")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String defaultParams() {
        return ServerHelper.defaultParamsString();
    }

    @GET
    @Path("solve")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String solve(
            @QueryParam("method") String method,
            @QueryParam("from") int from,
            @QueryParam("num") int num,
            @QueryParam("R") double R,
            @QueryParam("Q") double Q,
            @QueryParam("rho") double rho,
            @QueryParam("T0") double T0,
            @QueryParam("C") double C,
            @QueryParam("Tm") double Tm,
            @QueryParam("lambda") double lambda,
            @QueryParam("kappa") double kappa,
            @QueryParam("alpha") double alpha,
            @QueryParam("dt") double dt,
            @QueryParam("dz") double dz,
            @QueryParam("zNum") int zNum,
            @QueryParam("K") double K,
            @QueryParam("E") double E,
            @QueryParam("D") double D
    ) {
        try {
            // Note: when constructor changes, just assign some temporal values to new parameters
            Parameters params = new Parameters(
                    R,
                    Q,
                    rho,
                    T0,
                    C,
                    Tm,
                    lambda,
                    kappa,
                    alpha,
                    dt,
                    dz,
                    zNum,
                    K,
                    E,
                    D
            );

            StringBuilder answer = new StringBuilder();
            ExtendedSolver solver = RegisteredSolvers.solvers.get(method);
            if (solver == null)
                throw new IllegalArgumentException(String.format("'%s' method is not registered", method));

            List<TSection> solution = solver.extendedSolve(from + num, params)
                    .subList(from, from + num);

            answer.append(ServerHelper.solutionToString(solution))
                    .append("\n\n");

            String responseServiceInfo = String.format("%d", from);
            return answer
                    .append(responseServiceInfo)
                    .toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


}
