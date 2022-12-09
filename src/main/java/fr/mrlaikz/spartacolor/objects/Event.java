package fr.mrlaikz.spartacolor.objects;

import fr.mrlaikz.spartacolor.SpartaColor;
import fr.mrlaikz.spartacolor.enums.GameState;
import fr.mrlaikz.spartacolor.interfaces.FallListener;
import fr.mrlaikz.spartacolor.interfaces.PlayerListener;
import fr.mrlaikz.spartacolor.schedules.FallTimer;
import fr.mrlaikz.spartacolor.schedules.RespawnCaseTimer;
import fr.mrlaikz.spartacolor.schedules.SpawnTask;
import fr.mrlaikz.spartacolor.schedules.StartTask;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Event implements FallListener, PlayerListener {

    private List<Player> players;
    private int rounds;
    private List<Case> map;
    private BukkitTask timer;
    private GameState state;

    public Event(String map) {
        this.players = new ArrayList<Player>();
        this.rounds = 0;
        this.map = SpartaColor.getInstance().getMapManager().getMapCases(map);
        this.state = GameState.WAITING;
    }

    public GameState getState() {
        return state;
    }

    //GAME MANAGMENT
    public void pick() {
        Random r = new Random();
        int index = r.nextInt(15);
        Case c = map.get(index);
        //ANNONCE COULEUR
        FallTimer timer = new FallTimer(this, index, c);
        timer.run();
        RespawnCaseTimer respawnCaseTimer = new RespawnCaseTimer(this, index, c);
        timer.run();
    }

    public void reset(int index) {
        map.get(index).set();
    }

    //GAME PHASES
    public void prepare() {
        //BROADCAST + JOINABLE
    }

    public void preStart() {
        StartTask task = new StartTask(SpartaColor.getInstance());
        task.run();
    }

    public void start() {
        this.state = GameState.PLAYING;
        this.timer = Bukkit.getScheduler().runTaskAsynchronously(SpartaColor.getInstance(), new Runnable() {
            @Override
            public void run() {
                while(rounds != 5) {
                    SpawnTask task = new SpawnTask(SpartaColor.getInstance().getEventManager().getEvent());
                    task.run();
                    rounds++;
                }
            }
        });
    }

    public void stop() {
        timer.cancel();
        for(Player pl : players) {
            pl.teleport(SpartaColor.getInstance().getConfig().getLocation("locations.spawn"));
            pl.sendMessage("§aGAGNÉ");
        }
        SpartaColor.getInstance().getEventManager().stopEvent();
    }

    //PLAYER MANAGMENT
    public void removePlayer(Player p) {
        players.remove(p);
        if(players.size() == 1 && state.equals(GameState.PLAYING)) {
            stop();
        }
        if(p.isOnline()) {
            p.teleport(SpartaColor.getInstance().getConfig().getLocation("locations.spawn"));
        }
    }

    public void addPlayer(Player p) {
        players.add(p);
        p.teleport(SpartaColor.getInstance().getConfig().getLocation("locations.spawn"));
    }

    //INTERFACE
    @Override
    public void onFall(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        Location to = e.getTo();

        if(to.getY() < 30) {
            removePlayer(p);
        }
    }

    @Override
    public void onJoin(PlayerJoinEvent e) {
        addPlayer(e.getPlayer());
    }

    @Override
    public void onLeave(PlayerQuitEvent e) {
        removePlayer(e.getPlayer());
    }
}
