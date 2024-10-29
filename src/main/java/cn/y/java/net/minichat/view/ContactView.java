package cn.y.java.net.minichat.view;


import cn.y.java.net.minichat.core.ClientContext;
import cn.y.java.net.minichat.core.Message;
import cn.y.java.net.minichat.core.MsgType;
import cn.y.java.net.minichat.core.ui.TextView;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

public class ContactView extends TextView {

    private final Scanner scanner;
    private final ClientContext context;

    private final Map<String, Runnable> keyMap = new HashMap<>();

    public ContactView(Scanner scanner, ClientContext context) {
        this.scanner = scanner;
        this.context = context;
    }

    private void beforeRender(){
        keyMap.clear();
        Message msg = Message.getInstance();
        msg.setToken(context.getToken());
        msg.setMsgType(MsgType.USERS);
        Message respMsg = context.getMsgBus().sendMsgBlocked(msg);
        String userStr = respMsg.getBody();
        lines = new String[1];
        if (userStr != null && !userStr.isEmpty()) {
            String[] userArr = userStr.split(",");
            lines = new String[userArr.length + 1];
            for (int i = 0; i < userArr.length; i++) {
                String curUser = userArr[i];
                lines[i] = String.format("%d - chat with %s", i, curUser);
                keyMap.put(i + "", () -> {
                    System.out.printf("create chat with %s\n", curUser);
                    context.addData("friendName", curUser);
                    context.nextView();
                });
            }
        }
        lines[lines.length - 1] = "[r]efresh [q]uit";
        keyMap.put("r", this::render);
        keyMap.put("q", context::stop);
    }

    @Override
    public void render() {
        beforeRender();

        super.render();
        String key = scanner.nextLine();
        if (keyMap.containsKey(key)){
            keyMap.get(key).run();
        }else {
            System.out.println("invalid key");
            render();
        }
    }
}
