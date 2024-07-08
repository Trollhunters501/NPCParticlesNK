package xqwtxon.NPCParticlesNK.CreadoresProgram;
import cn.nukkit.scheduler.Task;
import cn.nukkit.level.particle.CriticalParticle;
import cn.nukkit.level.particle.DustParticle;
import cn.nukkit.math.Vector3;
import cn.nukkit.level.Level;
import cn.nukkit.entity.Entity;
import cn.nukkit.Nukkit;
import idk.plugin.npc.entities.NPC_Human;
import java.util.Map;
public class ParticleEffect extends Task{
  private int r = 0;
  private Main plugin;
  public ParticleEffect(Main p){
    this.plugin = p;
  }
  @Override
  public void onRun(int tick){
    if(this.r < 0){
      this.r++;
      return;
    }
    for(Integer subworld : this.plugin.getServer().getLevels().keySet()){
      Level world = this.plugin.getServer().getLevels().get(subworld);
      int colorA = this.plugin.getConfig().getInt("Color A");
      int colorB = this.plugin.getConfig().getInt("Color B");
      int colorC = this.plugin.getConfig().getInt("Color C");
      for(Entity entity : world.getEntities()){
        if(entity instanceof NPC_Human){
          double x = entity.getPosition().getX();
          double y = entity.getPosition().getY();
          double z = entity.getPosition().getZ();
          Level worlds = entity.getLevel();
          double hypo = 0.8;
          double a = (double) Math.cos(Math.toRadians(this.r / 0.09)) * hypo;
          double b = (double) Math.sin(Math.toRadians(this.r / 0.09)) * hypo;
          double time = (System.currentTimeMillis() - Nukkit.START_TIME);
          double seconds = Math.floor(time % 20);
          double up = (double) seconds / 5;
          Vector3 pos1 = new Vector3(x - a, y + up, z - b);
          Vector3 pos2 = new Vector3(x - b, y + up, z - a);
          DustParticle effect1 = new DustParticle(pos1, colorA, colorB, colorC);
          DustParticle effect2 = new DustParticle(pos2, colorA, colorB, colorC);
          worlds.addParticle(effect1);
          worlds.addParticle(effect2);
          this.r++;
        }
      }
    }
  }
}
