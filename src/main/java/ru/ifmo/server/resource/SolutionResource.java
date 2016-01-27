package ru.ifmo.server.resource;

import com.sun.jersey.spi.resource.Singleton;
import ru.ifmo.data.Parameters;
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
            @QueryParam("methods") String methods,
            @QueryParam("from") int from,
            @QueryParam("num") int num,
            @QueryParam("dx") double dx,
            @QueryParam("dt") double dt
    ) {
        try {
            StringBuilder answer = new StringBuilder();
            for (String method : methods.split(",")) {
                Solver solver = RegisteredSolvers.solvers.get(method);
                if (solver == null)
                    throw new IllegalArgumentException(String.format("'%s' method is not registered", method));

                List<TSection> solution = solver.solve(from + num, new Parameters())
                        .subList(from, from + num);

                answer.append(ServerHelper.solutionToString(solution))
                        .append("\n");
            }

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
