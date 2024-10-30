package cn.y.java.demo_projects.minichat.core.ui;

/**
 * 直角边框
 */
public class AngleBorder implements Border{
    @Override
    public char[] getBorderCharacters() {
        return new char[]{ '─', '│', '─', '│', '┌', '┐', '┘', '└' };
    }
}
