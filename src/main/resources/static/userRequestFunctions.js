function postUser() {

    var name = document.getElementById("newName").value;
    var postRequest = new XMLHttpRequest();

    postRequest.open("POST", 'users/' + name, true);

    postRequest.send();

    var UserCreatedBox = React.createClass({
			  render: function( ) {
				return <div> User {this.props.name} created! </div>;
			  }
    });

    ReactDOM.render(
      <UserCreatedBox name = {name} />,
      $("#signUpForm")[0]
    );
}

function getUser() {
	
	var name = document.getElementById("searchName").value;
    var request = new XMLHttpRequest();
	
	request.onreadystatechange = function () {
		if(request.readyState == 4){
			if(request.status != 200){
				alert(request.status + ": " + request.statusText);
			} else {
				var user = JSON.parse(request.responseText);

				var videos = user.videos;

				var Video = React.createClass({
						  render: function( ) {
							return(
									<div className="video"> {this.props.name} </div>
							);
						  }
				});

				var VideoList = React.createClass({
						  render: function( ) {
							return(
								<div className="videos">
										{
											videos.map(function(element) {
												return <Video key = {element.name} name = {element.name} />
											})
										}
								</div>
							);
						  }
				});


				ReactDOM.render(
					<VideoList />,
					$("#videosContent")[0]
				);

			}
		}
	}
	
	request.open("GET", "users/" + name, true);
	request.send();
}

$("#signUpButton").on("click", postUser);
$("#getUserButton").on("click", getUser);