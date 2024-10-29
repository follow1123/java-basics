package cn.y.java.net.minichat.core.ui;

import cn.y.java.net.minichat.core.ClientContext;
import cn.y.java.net.minichat.core.MsgBus;
import cn.y.java.net.minichat.view.ChatView;
import cn.y.java.net.minichat.view.ContactView;
import cn.y.java.net.minichat.view.LoginView;

import java.util.Scanner;

public class UILauncher {

    private final ClientContext context;
    private final ViewRegistrar viewRegistrar;

    public UILauncher(MsgBus msgBus) {
        this.viewRegistrar = new ViewRegistrar();
        this.context = new ClientContext(msgBus, viewRegistrar);
    }

    public void initView(Scanner scanner){
        viewRegistrar.registerView(new LoginView(scanner, context));
        viewRegistrar.registerView(new ContactView(scanner, context));
        viewRegistrar.registerView(new ChatView(scanner, context));
    }

    public void start(){
        Scanner scanner = new Scanner(System.in);
        initView(scanner);
        System.out.println("start render");
        while (context.isRunning()){
            if (context.getToken() == null || context.getToken().isEmpty()){
                viewRegistrar.getAuthView().render();
                continue;
            }
            viewRegistrar.getCurrView().render();
        }
        context.stop();
        System.out.println("end render");
    }

}
