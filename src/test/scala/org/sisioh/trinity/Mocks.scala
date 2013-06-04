package org.sisioh.trinity

case class Post(val title:String)

class PostsListView(val posts:List[Post]) extends View {
  val template  = "posts.mustache"
  contentType   = Some("text/plain")
}

class PostsView(val posts:List[Post]) extends View {
  val template      = "posts_layout.mustache"
  val postsListView = new PostsListView(posts)

  def body          = postsListView.render
}
