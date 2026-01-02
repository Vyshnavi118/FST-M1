package examples;

import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import io.appium.java_client.android.AndroidDriver;

public class ActionsBase {

    public static void longPress(AndroidDriver driver, Point point) {

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence longPress = new Sequence(finger, 1);

        // Move to position
        longPress.addAction(finger.createPointerMove(
                Duration.ZERO,
                PointerInput.Origin.viewport(),
                point.getX(),
                point.getY()
        ));

        // Finger down
        longPress.addAction(
                finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg())
        );

        // ✅ PAUSE belongs to Sequence
        longPress.addAction(
                new org.openqa.selenium.interactions.Pause(finger, Duration.ofSeconds(2))
        );

        // Finger up
        longPress.addAction(
                finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg())
        );

        driver.perform(Arrays.asList(longPress));
    }

    public static void doSwipe(AndroidDriver driver, Point start, Point end, int durationMillis) {

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(
                Duration.ZERO,
                PointerInput.Origin.viewport(),
                start.getX(),
                start.getY()
        ));

        swipe.addAction(
                finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg())
        );

        swipe.addAction(finger.createPointerMove(
                Duration.ofMillis(durationMillis),
                PointerInput.Origin.viewport(),
                end.getX(),
                end.getY()
        ));

        swipe.addAction(
                finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg())
        );

        driver.perform(Arrays.asList(swipe));
    }
}
