// PinpointRawPassthroughEncoder.java
package org.firstinspires.ftc.teamcode.RR.tuning;

import com.acmerobotics.roadrunner.ftc.Encoder;
import com.acmerobotics.roadrunner.ftc.GoBildaPinpointDriverRR;
import com.acmerobotics.roadrunner.ftc.PositionVelocityPair;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotorSimple.Direction;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.RR.GoBildaPinpointDriver;
import org.jetbrains.annotations.NotNull;

@Metadata(
        mv = {2, 0, 0},
        k = 1,
        xi = 48,
        d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0005¢\u0006\u0002\u0010\nJ\b\u0010\u0019\u001a\u00020\u001aH\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"},
        d2 = {"Lcom/acmerobotics/roadrunner/ftc/PinpointRawPassthroughEncoder;", "Lcom/acmerobotics/roadrunner/ftc/Encoder;", "pinpoint", "Lcom/acmerobotics/roadrunner/ftc/GoBildaPinpointDriverRR;", "usePerpendicular", "", "reversed", "anyDummyMotor", "Lcom/qualcomm/robotcore/hardware/DcMotor;", "autoUpdate", "(Lcom/acmerobotics/roadrunner/ftc/GoBildaPinpointDriverRR;ZZLcom/qualcomm/robotcore/hardware/DcMotor;Z)V", "controller", "Lcom/qualcomm/robotcore/hardware/DcMotorController;", "getController", "()Lcom/qualcomm/robotcore/hardware/DcMotorController;", "direction", "Lcom/qualcomm/robotcore/hardware/DcMotorSimple$Direction;", "getDirection", "()Lcom/qualcomm/robotcore/hardware/DcMotorSimple$Direction;", "setDirection", "(Lcom/qualcomm/robotcore/hardware/DcMotorSimple$Direction;)V", "lastPos", "", "lastTime", "", "getPositionAndVelocity", "Lcom/acmerobotics/roadrunner/ftc/PositionVelocityPair;", "sources for library Gradle: page.j5155.roadrunner:ftc-otos:0.1.14+0.3.1@aar"}
)
public final class PinpointRawPassthroughEncoder implements Encoder {
    @NotNull
    private final GoBildaPinpointDriver pinpoint;
    private final boolean usePerpendicular;
    private final boolean reversed;
    @NotNull
    private final DcMotor anyDummyMotor;
    private final boolean autoUpdate;
    @NotNull
    private DcMotorSimple.Direction direction;
    private double lastPos;
    private long lastTime;

    public PinpointRawPassthroughEncoder(@NotNull GoBildaPinpointDriver pinpoint, boolean usePerpendicular, boolean reversed, @NotNull DcMotor anyDummyMotor, boolean autoUpdate) {
        super();
        Intrinsics.checkNotNullParameter(pinpoint, "pinpoint");
        Intrinsics.checkNotNullParameter(anyDummyMotor, "anyDummyMotor");
        this.pinpoint = pinpoint;
        this.usePerpendicular = usePerpendicular;
        this.reversed = reversed;
        this.anyDummyMotor = anyDummyMotor;
        this.autoUpdate = autoUpdate;
        this.direction = Direction.FORWARD;
        this.lastTime = System.nanoTime();
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
        if (this.autoUpdate) {
            this.pinpoint.update();
        }

        double pos = (double)0.0F;
        double vel = (double)0.0F;
        if (this.usePerpendicular) {
            pos = DistanceUnit.INCH.fromMm((double)((float)this.pinpoint.getEncoderY() / this.pinpoint.getCurrentTicksPerMM()));
        } else {
            pos = DistanceUnit.INCH.fromMm((double)((float)this.pinpoint.getEncoderX() / this.pinpoint.getCurrentTicksPerMM()));
        }

        if (this.reversed) {
            pos *= (double)-1;
        }

        if (this.lastPos == (double)0.0F) {
            this.lastPos = pos;
            vel = (double)0.0F;
        } else {
            long currentTime = System.nanoTime();
            double timeDiffSec = (double)(currentTime - this.lastTime) * 1.0E-9;
            vel = (pos - this.lastPos) / timeDiffSec;
            this.lastPos = pos;
            this.lastTime = currentTime;
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
