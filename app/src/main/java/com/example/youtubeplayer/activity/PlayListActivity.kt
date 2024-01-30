package com.example.youtubeplayer.activity

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.youtubeplayer.R
import com.example.youtubeplayer.constant.Strings
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class PlayListActivity: Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.setContentView(R.layout.playlist_activity)

        // 「youtubePlayListView」でプレイリストが再生されるように更新
        this.settingPlayListView()
    }

    /**
     * 「youtubePlayListView」でプレイリストを再生できるように設定
     */
    private fun settingPlayListView() {
        val youtubeView = this.findViewById<YouTubePlayerView>(R.id.youtubePlayListView)

        val playlistId = this.intent.getStringExtra(Strings.KEY_PLAY_LIST_ID)

        if(playlistId is String) {
            // プレイリスト指定用オプションを生成
            val option = IFramePlayerOptions.Builder()
                .controls(1)
                .listType("playlist")
                .list(playlistId) // プレイリストID
                .build()

            // 「youtube_player_view」を再構築
            youtubeView.enableAutomaticInitialization = false
            youtubeView.enableBackgroundPlayback(true)
            youtubeView.initialize(object : AbstractYouTubePlayerListener() {}, option)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        // 「youtubePlayListView」の破棄
        val youtubeView = this.findViewById<YouTubePlayerView>(R.id.youtubePlayListView)
        youtubeView.release()
    }
}