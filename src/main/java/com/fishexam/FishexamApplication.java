package com.fishexam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 程序入口
 */
@SpringBootApplication
public class FishexamApplication {
    public static void main(String[] args) {
        SpringApplication.run(FishexamApplication.class, args);
        // banner图可以随意更换,生成地址:https://www.bootschool.net/ascii
        System.out.println(" ████████ ██                                       ███████             ██ ██                          \n" +
                "░░░░░░██ ░██               ██   ██                ██░░░░░██           ░██░░                           \n" +
                "     ██  ░██      ██   ██ ░░██ ██  ██   ██       ██     ░░██ ███████  ░██ ██ ███████   █████          \n" +
                "    ██   ░██████ ░██  ░██  ░░███  ░██  ░██      ░██      ░██░░██░░░██ ░██░██░░██░░░██ ██░░░██         \n" +
                "   ██    ░██░░░██░██  ░██   ░██   ░██  ░██      ░██      ░██ ░██  ░██ ░██░██ ░██  ░██░███████         \n" +
                "  ██     ░██  ░██░██  ░██   ██    ░██  ░██      ░░██     ██  ░██  ░██ ░██░██ ░██  ░██░██░░░░          \n" +
                " ████████░██  ░██░░██████  ██     ░░██████       ░░███████   ███  ░██ ███░██ ███  ░██░░██████ ░██ ░██ ░██\n" +
                "░░░░░░░░ ░░   ░░  ░░░░░░  ░░       ░░░░░░         ░░░░░░░   ░░░   ░░ ░░░ ░░ ░░░   ░░  ░░░░░░  ░░  ░░  ░░ \n");
    }
}
