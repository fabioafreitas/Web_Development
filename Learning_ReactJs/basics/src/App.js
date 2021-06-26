import React from "react";

import Post from "./components/Post";

export default class App extends React.Component {
  render() {
    return (
      <div class="container">
        <h1>Hello World</h1>
        <Post title="Covid-19" />
        <Post title="Bolsonaro Burro" />
        <Post title="Brasil" />
      </div>
    );
  }
}
