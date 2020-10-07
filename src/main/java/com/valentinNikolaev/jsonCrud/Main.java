package com.valentinNikolaev.jsonCrud;

import com.valentinNikolaev.jsonCrud.view.MainView;

public class Main {

    public static void main(String[] args) {
        try {
            MainView mainView = new MainView();
            mainView.initiateMainView();
        } catch (ClassNotFoundException | IllegalArgumentException e) {
            e.getMessage();
        }
    }
}
