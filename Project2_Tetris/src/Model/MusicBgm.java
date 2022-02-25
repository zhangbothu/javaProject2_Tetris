package Model;

import java.applet.AudioClip;
/*
 * 利用AudioClip实现系统音乐的播放
 */
import java.io.*;
import java.applet.Applet;
import java.net.MalformedURLException;
import java.net.URL;
@SuppressWarnings("deprecation")
public class MusicBgm {
/*
 * 利用AudioClip类实现了背景音乐与游戏音效,
 * 其中的blockMoving方法在按键被按下时会被调用;
 * bgmPlay方法中调用了AudioClip类中的loop()方法，实现了背景音乐的循环播放。
 */
    private static String bgmUrl="Resources\\Music\\bgmok.wav";
    private static String actionUrl="Resources\\Music\\action3.wav";
    private static AudioClip bgmAC;
    private static AudioClip moveAC;
    static volatile boolean isRunning =false;
    static volatile boolean isTurnOn =true;
    public static boolean isRunning(){
        return isRunning;
    }
    public static boolean isturnOn(){
    	return isTurnOn;
    }
    public static void setturnOn(Boolean turn){
    	isTurnOn=turn;
    }
    public static void blockMoving(){
    	if(!isTurnOn)
    		return;
    	if(moveAC==null){
    		try {
                URL urlForcb;
                File file = new File(actionUrl);
                urlForcb = file.toURL();
                moveAC = Applet.newAudioClip(urlForcb);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
    	}
    	try{
    		moveAC.play();
    	}catch(Exception exception){
    		exception.printStackTrace();
    	}
    }
    
    public static void bgmBeginPlay(){
    	if(!isTurnOn)
    		return;
    	if(bgmAC==null){
    		try {
                URL urlForcb;
                File file = new File(bgmUrl);
                urlForcb = file.toURL();
                bgmAC = Applet.newAudioClip(urlForcb);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
    	}
    	try{
    		bgmAC.loop();
    		isRunning =true;
    	}catch(Exception exception){
    		exception.printStackTrace();
    	}
    	
    }
    public static void bgmStop(){
    	try{
            bgmAC.stop();
            isRunning =false;
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public static void bgmReplay(){
    	try{
            bgmAC.stop();
            isRunning =false;
        }catch (Exception e){
            e.printStackTrace();
        }
    }
   
}

