import static org.junit.Assert.*;

import org.junit.*;

//import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.PWMSparkMax;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

public class TestAutonomous {
    public static final double DELTA = 1e-2;

    PWMSparkMax m_leftMotor_front;
    PWMSparkMax m_leftMotor_rear;
    PWMSparkMax m_rightMotor_front;
    PWMSparkMax m_rightMotor_rear;
    SpeedControllerGroup m_leftSide;
    SpeedControllerGroup m_rightSide;

    @Before
    public void setup() {
        m_leftMotor_front = new PWMSparkMax(0);
        m_leftMotor_rear = new PWMSparkMax(1);
        m_rightMotor_front = new PWMSparkMax(2);
        m_rightMotor_rear = new PWMSparkMax(3);

        m_leftSide = new SpeedControllerGroup(m_leftMotor_front, m_leftMotor_rear);
        m_rightSide = new SpeedControllerGroup(m_rightMotor_front, m_rightMotor_rear);
    }

    @After
    public void shutdown() throws Exception {
        m_leftSide.stopMotor();
        m_rightSide.stopMotor();
    }

    @Test
    public void movesForward() {
        m_leftSide.set(1);
        m_rightSide.set(1);
        assertEquals(1.0, m_leftMotor_front.get(), DELTA);
    }

}
