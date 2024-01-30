package com.example.youtubeplayer

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.youtubeplayer.activity.PlayListActivity
import com.example.youtubeplayer.constant.Strings
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_main)

        // Youtube View の表示設定を処理
        this.settingYoutubeView()

        // btnVideoIdChange を設定
        this.settingBtnVideoIdChange()

        // DOLLS 再生
        this.setBtnEventForLoadVideo(R.id.btnLoadDolls, "4DpOIXe4yUM")

        // 月光花 再生
        this.setBtnEventForLoadVideo(R.id.btnLoadGekkouka, "0r399LOfHG8")

        // ヴァンパイア再生
        this.setBtnEventForLoadVideo(R.id.btnLoadVampire, "T96oVm3IkoA")

        // アイドル再生
        this.setBtnEventForLoadVideo(R.id.btnLoadIdol, "ZRtdQ81jPUQ")

        // ボタン押下でプレイリストを再生
        this.findViewById<Button>(R.id.btnChangePlayList)
            .setOnClickListener { this.startPlayList() }
    }

    /** プレイリストを再生する */
    private fun startPlayList() {

        // エディタに入力された「リストID」を元に、プレイリストを再生
        val editor = this.findViewById<EditText>(R.id.editorPlayListId)
        val editorText = editor.text

        if (editorText != null) {
            // 新規ページ生成して、ポップアップ
            val newIntent = Intent(this, PlayListActivity::class.java).apply {
                putExtra(Strings.KEY_PLAY_LIST_ID, editorText.toString())
            }
            this.startActivity(newIntent)
        }
    }

    /** 破棄イベント */
    override fun onDestroy() {
        // 「youtube_player_view」を破棄
        this.findViewById<YouTubePlayerView>(R.id.youtube_player_view).release()

        super.onDestroy()
    }

    /**
     * ボタン押下時に、
     * @param btnId ボタン ID
     * @param videoId ビデオ ID
     */
    private fun setBtnEventForLoadVideo(btnId: Int, videoId: String) {
        this.findViewById<Button>(btnId).setOnClickListener {
            this.loadNewVideoId(videoId)
        }
    }

    /**
     * video ID の動画を「youtube_player_view」で再生
     * @param videoId ビデオ ID
     */
    private fun loadNewVideoId(videoId: String) {

        // 新しい video ID でロードする
        val youtubeView = this.findViewById<YouTubePlayerView>(R.id.youtube_player_view)
        youtubeView.getYouTubePlayerWhenReady(object : YouTubePlayerCallback {

            // youTubePlayer が初期化済みなら処理
            override fun onYouTubePlayer(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(videoId, 0f)
            }
        })
    }

    /**
     * btnVideoIdChange を設定
     */
    private fun settingBtnVideoIdChange() {
        // btnVideoIdChange のクリックイベントを設定
        val btn = this.findViewById<Button>(R.id.btnVideoIdChange)
        btn.setOnClickListener {

            // エディタ に入力された video ID で更新
            val editor = this.findViewById<EditText>(R.id.editorVideoId)
            if (editor.text != null) {
                this.loadNewVideoId(editor.text.toString())
            }
        }
    }

    /**
     * Youtube View の表示設定を処理
     */
    private fun settingYoutubeView() {
        val view = this.findViewById<YouTubePlayerView>(R.id.youtube_player_view)
        view.enableBackgroundPlayback(true) // バックグラウンドでの再生を許可
    }
}