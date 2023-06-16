package cn.edu.whut.sept.zuul.ZuulCommands;

import cn.edu.whut.sept.zuul.Game.Room;

/**
 * 
 * 此类用于控制错误的退出异常
 *
 */
public class BadExitException extends Exception {

    private static final long serialVersionUID = -6960984107626797656L;

    public BadExitException(String direction, Room r)
    {
        super(direction == null && r == null
                ? "Direction and room are null"
                : direction == null
                        ? "Direction is null"
                        : r == null
                                ? "Room is null"
                                : "Something bad about an exit");
    }
}
