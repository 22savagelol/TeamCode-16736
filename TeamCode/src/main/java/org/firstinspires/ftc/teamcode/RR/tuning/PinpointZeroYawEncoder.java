// PinpointZeroYawEncoder.java
package org.firstinspires.ftc.teamcode.RR.tuning;

import android.util.Log;

import com.acmerobotics.roadrunner.ftc.Encoder;
import com.acmerobotics.roadrunner.ftc.PositionVelocityPair;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotorSimple.Direction;
import com.qualcomm.robotcore.util.RobotLog;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

import org.firstinspires.ftc.teamcode.RR.GoBildaPinpointDriver;
import org.jetbrains.annotations.NotNull;

@Metadata(
        mv = {2, 0, 0},
        k = 1,
        xi = 48,
        d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u0013\u001a\u00020\u0014H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"},
        d2 = {"Lcom/acmerobotics/roadrunner/ftc/PinpointZeroYawEncoder;", "Lcom/acmerobotics/roadrunner/ftc/Encoder;", "pinpoint", "Lcom/acmerobotics/roadrunner/ftc/GoBildaPinpointDriverRR;", "usePerpendicular", "", "anyDummyMotor", "Lcom/qualcomm/robotcore/hardware/DcMotor;", "(Lcom/acmerobotics/roadrunner/ftc/GoBildaPinpointDriverRR;ZLcom/qualcomm/robotcore/hardware/DcMotor;)V", "controller", "Lcom/qualcomm/robotcore/hardware/DcMotorController;", "getController", "()Lcom/qualcomm/robotcore/hardware/DcMotorController;", "direction", "Lcom/qualcomm/robotcore/hardware/DcMotorSimple$Direction;", "getDirection", "()Lcom/qualcomm/robotcore/hardware/DcMotorSimple$Direction;", "setDirection", "(Lcom/qualcomm/robotcore/hardware/DcMotorSimple$Direction;)V", "getPositionAndVelocity", "Lcom/acmerobotics/roadrunner/ftc/PositionVelocityPair;", "sources for library Gradle: page.j5155.roadrunner:ftc-otos:0.1.14+0.3.1@aar"}
)
public final class PinpointZeroYawEncoder implements Encoder {
    @NotNull
    private final GoBildaPinpointDriver pinpoint;
    private final boolean usePerpendicular;
    @NotNull
    private final DcMotor anyDummyMotor;
    @NotNull
    private DcMotorSimple.Direction direction;

    public PinpointZeroYawEncoder(@NotNull GoBildaPinpointDriver pinpoint, boolean usePerpendicular, @NotNull DcMotor anyDummyMotor) {
        super();
        Intrinsics.checkNotNullParameter(pinpoint, "pinpoint");
        Intrinsics.checkNotNullParameter(anyDummyMotor, "anyDummyMotor");
        this.pinpoint = pinpoint;
        this.usePerpendicular = usePerpendicular;
        this.anyDummyMotor = anyDummyMotor;
        Log.println(Log.INFO, "PinpointEncoder", "init: Initializing pinpoint encoder in tuning mode");
        Log.println(Log.INFO, "PinpointEncoder", "init: Old yaw scalar = " + this.pinpoint.getYawScalar());
        Log.println(Log.WARN, "PinpointEncoder", "init: Setting Pinpoint yaw scalar to 0. Perform power cycle to reset");
        Object[] var4 = new Object[]{this.pinpoint.getYawScalar()};
        RobotLog.addGlobalWarningMessage("Disabling Pinpoint IMU. Perform a power cycle (turn the robot off and back on again) to reset it before running Feedback Tuner, LocalizationTest, or an auto (Angular Scalar now 0, previously %f)", var4);
        this.pinpoint.setYawScalar((double)0.0F);
        this.pinpoint.resetPosAndIMU();
        this.direction = Direction.FORWARD;
    }

    @NotNull
    public DcMotorSimple.Direction getDirection() {
        return this.direction;
    }

    public void setDirection(@NotNull DcMotorSimple.Direction var1) {
        Intrinsics.checkNotNullParameter(var1, "<set-?>");
        this.direction = var1;
    }

    @NotNull
    public PositionVelocityPair getPositionAndVelocity() {
        this.pinpoint.update();
        double pos = (double)0.0F;
        double vel = (double)0.0F;
        if (this.usePerpendicular) {
            pos = this.pinpoint.getPositionRR().position.y;
            vel = this.pinpoint.getVelocityRR().linearVel.y;
        } else {
            pos = this.pinpoint.getPositionRR().position.x;
            vel = this.pinpoint.getVelocityRR().linearVel.x;
        }

        return new PositionVelocityPair(pos, vel, pos, vel);
    }

    @NotNull
    public DcMotorController getController() {
        DcMotorController var1 = this.anyDummyMotor.getController();
        Intrinsics.checkNotNullExpressionValue(var1, "getController(...)");
        return var1;
    }
}
