package frc.robot.commands.autoCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Climber;
// Added to fix pathplanner bug
public class autoclimb extends SequentialCommandGroup{
    public autoclimb(Climber mClimber, boolean dirUp) {
        if(dirUp){
            addCommands(new ExtendClimber(mClimber));
        }else{
            addCommands(new RetractClimber(mClimber));
        }
    }
}
