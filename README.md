### 以下、ライブラリを参照

https://github.com/PierfrancescoSoffritti/android-youtube-player

### バックグラウンド再生について

```
// 初期化済の「YouTubePlayerView」に対して、.enableBackgroundPlayback(true) を設定すると、バックグラウンド再生が可能
YouTubePlayerView.enableBackgroundPlayback(true)
```

一応、Android のライフサイクルに組み込む方法があるが、基本的には、`onDestroy()`時に、`.release()`することを推奨

```
override fun onDestroy() {
  YouTubePlayerView.release();
  super.onDestroy()
}
```

### プレイリスト再生について

以下の様なコードを実装することで、プレイリスト再生に対応する
```
val option = IFramePlayerOptions.Builder()
  .controls(1)
  .listType("playlist")
  .list(playlistId) // <- プレイリストID をここに設定
  .build()

let youtubeView = YouTubePlayerView(this)

// .initialize() は、「YouTubePlayerView」が初期化されていない状態で実行しないとエラーになる。
// 「enableAutomaticInitialization」が false である必要がある
youtubeView.enableAutomaticInitialization = false
youtubeView.initialize(object : AbstractYouTubePlayerListener() {}, option)
```
