package ru.ifmo.server.resource;

import com.sun.jersey.spi.resource.Singleton;
import ru.ifmo.data.Parameters;
import ru.ifmo.data.SimpleTSection;
import ru.ifmo.data.TSection;
import ru.ifmo.methods.RegisteredSolvers;
import ru.ifmo.methods.Solver;
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
    @Path("solve")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String solve(
            @QueryParam("method") String method,
            @QueryParam("from") int from,
            @QueryParam("num") int num,
            @QueryParam("dz") double dz,
            @QueryParam("dt") double dt
    ) {
        try {
            StringBuilder answer = new StringBuilder();
            Solver solver = RegisteredSolvers.solvers.get(method);
            if (solver == null)
                throw new IllegalArgumentException(String.format("'%s' method is not registered", method));

            Parameters params = new Parameters(1, dt, dz);
            List<SimpleTSection> presolution = solver.solve(from + num, params)
                    .subList(from, from + num);
            List<TSection> solution = TSection.extend(presolution);

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
