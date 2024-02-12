package com.example.youtubeplayer.activity

import android.app.Activity
import android.os.Bundle
import android.widget.Button
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

        this.setVideoSheIsLegend()
    }

    /**
     * 「She is legend」再生ボタン
     */
    private fun setVideoSheIsLegend() {
        // this.findViewById<Button>(R.id.she_is_legend).setOnClickListener {

        // 「youtubePlayListView」でプレイリストが再生されるように更新
        this.settingPlayListView("PLYbqsCP6tq2pybnZCHizsG9xlleshRh6V")
    }

    /**
     * 「youtubePlayListView」でプレイリストを再生できるように設定
     */
    private fun settingPlayListView(playlistId: String) {
        val youtubeView = this.findViewById<YouTubePlayerView>(R.id.youtubePlayListView)

        // val playlistId = this.intent.getStringExtra(Strings.KEY_PLAY_LIST_ID)

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

    override fun onDestroy() {
        super.onDestroy()

        // 「youtubePlayListView」の破棄
        val youtubeView = this.findViewById<YouTubePlayerView>(R.id.youtubePlayListView)
        youtubeView.release()
    }
}