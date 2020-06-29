package otletek;

import javafx.scene.control.TableColumn;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BasicController {

    /*public List<Method> getMethodok(Class returnObj){
        return Arrays.stream(this.getClass().getMethods())
                .filter(m -> m.getReturnType().equals(returnObj)).collect(Collectors.toList());

    }*/

    public List<Method> getMethodok(Class returnObj){
        return Arrays.stream(this.getClass().getMethods()).filter(method -> method.getReturnType().equals(returnObj))
                .collect(Collectors.toList());

    }

    public List<Method> getTableColumnGetMethodok(){
        return Arrays.stream(this.getClass().getMethods()).filter(method -> method.getReturnType()
                .equals(new TableColumn().getClass()))
                .collect(Collectors.toList());

    }
}
