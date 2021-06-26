import React from "react";

import Comment from "./Comment";

export default class Post extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      comments: [],
      newCommentText: ""
    };

    this.handlerSubmit = this.handlerSubmit.bind(this);
    this.handlerTextChange = this.handlerTextChange.bind(this);
  }

  handlerSubmit(e) {
    this.setState({
      comments: [...this.state.comments, { text: this.state.newCommentText }]
    });
    this.setState({ newCommentText: "" });
    e.preventDefault();
  }

  handlerTextChange(e) {
    this.setState({ newCommentText: e.target.value });
  }

  render() {
    return (
      <div>
        <h2>{this.props.title}</h2>
        <form onSubmit={this.handlerSubmit}>
          <input
            value={this.state.newCommentText}
            onChange={this.handlerTextChange}
          />
          <button class="waves-effect waves-light btn" type="submit">
            Comentar
          </button>
        </form>
        {this.state.comments.map((comment, index) => {
          return <Comment key={index} text={comment.text} />;
        })}
      </div>
    );
  }
}
