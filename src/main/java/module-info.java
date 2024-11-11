module projektavimas.checkers {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;


    opens projektavimas.checkers to javafx.fxml;
    exports projektavimas.checkers;
    exports projektavimas.fxControllers;
    opens projektavimas.fxControllers to javafx.fxml;
}