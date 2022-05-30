package week33;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution3 {
    class Song {
        String title, song;
        int time;

        public Song(String title, String song, int time) {
            this.title = title;
            this.song = song;
            this.time = time;
        }

        public String getTitle() {
            return title;
        }

        public String getSong() {
            return song;
        }

        public int getTime() {
            return time;
        }
    }

    public String solution(String m, String[] musicinfos) {
        List<Song> songs = new ArrayList<>();

        Song answerSong = new Song("(None)", "", -1);

        for (String musicInfo : musicinfos) {
            String[] music = musicInfo.split(",");
            int playTime = getPlayTime(music[0], music[1]);

            String playedSong = getPlayedSong(music, playTime);

            songs.add(new Song(music[2], playedSong, playTime));
        }

        String listenedSong = getSong(m);

        for (Song song : songs) {
            if (song.getSong().contains(listenedSong)) {
                if (song.getTime() > answerSong.getTime()) {
                    answerSong = song;
                }
            }
        }
        return answerSong.getTitle();
    }

    private String getPlayedSong(String[] music, int playTime) {
        String song = getSong(music[3]);

        int songTime = song.length();

        int replay = playTime / songTime;
        int partPlay = playTime % songTime;

        return song.repeat(replay) + song.substring(0, partPlay);
    }

    private String getSong(String music) {
        List<String> sounds = new ArrayList<>();

        for (String sound : music.split("")) {
            if (sound.equals("#")) {
                sounds.set(sounds.size() - 1, sounds.get(sounds.size() - 1).toLowerCase());
            } else {
                sounds.add(sound);
            }
        }
        return String.join("", sounds);
    }

    private int getPlayTime(String start, String end) {
        int[] startInfo = Arrays.stream(start.split(":"))
                .mapToInt(Integer::parseInt).toArray();
        int[] endInfo = Arrays.stream(end.split(":"))
                .mapToInt(Integer::parseInt).toArray();

        return (endInfo[0] - startInfo[0]) * 60 + endInfo[1] - startInfo[1];
    }
}