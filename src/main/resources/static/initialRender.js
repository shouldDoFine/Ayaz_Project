ReactDOM.render(
	 <h1>
		<a href="index.html">WATCHTUBE</a>
	</h1>,
	$("#header")[0]
);

ReactDOM.render(
	<form>
		<p align="center">Username:
			<input id="newName" type="text" name="name" size="15"/>
		</p>
		<input id="signUpButton" className="button" type="button" value="Sign-up"/>
	</form>,
	$("#signUpForm")[0]
);

ReactDOM.render(
	<form>
		<p align="center">Username:
			<input id="searchName" type="text" name="name" size="15"/>
		</p>
		<input id="getUserButton" className="button" type="button" value="Search user and show videos"/>
	</form>,
	$("#userSearchForm")[0]
);