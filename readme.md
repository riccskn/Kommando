# Kommando
Kommando is a lightweight Java utility library designed to simplify the creation and handling of commands in Nukkit, a Minecraft: Bedrock Edition server software. It leverages the Strategy design pattern to reduce the use of switch/case statements by isolating each command flow into separate, modular classes—resulting in cleaner, more maintainable code.

## How to use
At your `pom.xml` put

```xml
<repositories>
        <repository>
            <id>github</id>
            <name>GitHub Packages</name>
            <url>https://maven.pkg.github.com/riccskn/Kommando</url>
        </repository>
    </repositories>
```

then

```xml
<dependencies>
    <dependency>
        <groupId>com.github.riccskn</groupId>
        <artifactId>kommando</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
</dependency>
```

## Example commmand


### Command
```java
package example.test;

import cn.nukkit.command.CommandSender;
import com.br.riccskn.kommando.BaseCommand;
import com.br.riccskn.kommando.enums.ErrorCodeEnum;

public class TestCommand extends BaseCommand {

    public TestCommand() {
        super("test"); //command name
    }

    @Override
    protected void prepare() {
        registerSubCommand("test", new TestSubCommand()); //registering a subcommand
        registerErrorMessage(ErrorCodeEnum.SUBCOMMAND_NOT_FOUND, "This subcommand does not exist.");
    }

    public void onRun(CommandSender commandSender, String[] args) {
        commandSender.sendMessage("AAA");
    }
}

```

### SubCommand
```java
package example.test;

import cn.nukkit.command.CommandSender;
import com.br.riccskn.kommando.BaseSubCommand;


public class TestSubCommand extends BaseSubCommand {

    @Override
    protected void prepare() {
    }

    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage("Your message is: "+args[0]);
    }

}

```
