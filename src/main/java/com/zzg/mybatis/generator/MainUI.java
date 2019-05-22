package com.zzg.mybatis.generator;

import com.zzg.mybatis.generator.controller.MainUIController;
import com.zzg.mybatis.generator.controller.PersonOverviewController;
import com.zzg.mybatis.generator.util.ConfigHelper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;

/**
 * 这是本软件的主入口,要运行本软件请直接运行本类就可以了,不用传入任何参数
 * 本软件要求jkd版本大于1.8.0.40
 */
public class MainUI extends Application {
    public static Stage primaryStage;

    private static final Logger _LOG = LoggerFactory.getLogger(MainUI.class);

    /**
     * 一切看起来象是剧场里表演: 这里的 Stage 是一个主容器，它就是我们通常所认为的窗口（有边，高和宽，还有关闭按钮）。
     * 在这个 Stage 里面，你可以放置一个 Scene，当然你可以切换别的 Scene，
     * 而在这个 Scene 里面，我们就可以放置各种各样的控件。
     *
     * @param primaryStage stage 作为舞台,主容器 承载(scene, 其他ui控件[media player,text,image view])
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        MainUI.primaryStage = primaryStage;
        // this.primaryStage.setTitle("AddressApp");

        ConfigHelper.createEmptyFiles();

        //加载 root layout from fxml file.
        URL url = Thread.currentThread().getContextClassLoader().getResource("fxml/MainUI.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Parent root = fxmlLoader.load();

        // Show the scene containing the root layout.
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(true);
        primaryStage.show();

        // main ui 加载数据
        MainUIController controller = fxmlLoader.getController();
        controller.setPrimaryStage(primaryStage);

        //切换到新的 scene
       // showPersonOverview();
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showPersonOverview() {
        try {
            // Load person overview.
            // FXMLLoader loader = new FXMLLoader();
            //loader.setLocation(MainUI.class.getResource("view/PersonOverview.fxml"));

            URL url = Thread.currentThread().getContextClassLoader().getResource("fxml/PersonOverview.fxml");
            FXMLLoader loader = new FXMLLoader(url);

            AnchorPane personOverview =  loader.load();
            // 将stage 舞台切换到新的 scene
            primaryStage.setScene(new Scene(personOverview));

            // 把主舞台的引用传递给 controller( 其实声明静态的就不用再传递了)
            PersonOverviewController controller = loader.getController();
            controller.setPrimaryStage(primaryStage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }


    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

}
