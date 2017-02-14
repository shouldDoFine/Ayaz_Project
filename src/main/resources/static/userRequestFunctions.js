function postUser() {

    var name = document.getElementById("newName").value;
    var postRequest = new XMLHttpRequest();

    postRequest.open("POST", 'users/' + name, false);

    postRequest.send();

    var UserCreatedBox = React.createClass({
			  render: function( ) {
				return <div> User {this.props.name} created! </div>;
			  }
    });

    ReactDOM.render(
      <UserCreatedBox name = {name} />,
      document.getElementById("signUpForm")
    );
}

function getUser() {
	
	var name = document.getElementById("searchName").value;
    var request = new XMLHttpRequest();
	
	request.open("GET", "users/" + name, false);
	
	request.send();
	
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
            document.getElementById("videosContent")
        );

	}
}

window.onload = document.getElementById("signUpButton").addEventListener("click", postUser, false);
window.onload = document.getElementById("getUserButton").addEventListener("click", getUser, false);