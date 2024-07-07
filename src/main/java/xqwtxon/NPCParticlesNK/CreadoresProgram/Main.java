package xqwtxon.NPCParticlesNK.CreadoresProgram;
import cn.nukkit.plugin.PluginBase;
import java.io.File;
public class Main extends PluginBase{
  @Override
  public void onLoad(){
    this.saveResource("config.yml");
    if(this.getConfig().get("config-version") == "1.0.1"){
      File confold = new File(this.getDataFolder()+"/config.yml");
      confold.renameTo(new File(this.getDataFolder()+"/old-config.yml"));
      this.getLogger().notice("Your configuration is outdated! The configuration was renamed as old-config.yml");
      this.saveResource("config.yml");
    }else{
      this.saveDefaultConfig();
    }
  }
  @Override
  public void onEnable(){
    int colorA = this.getConfig().getInt("Color A");
    int colorB = this.getConfig().getInt("Color B");
    int colorC = this.getConfig().getInt("Color C");
    this.saveDefaultConfig();
    this.getServer().getScheduler().scheduleRepeatingTask(new ParticleEffect(this), 2);
  }
}
