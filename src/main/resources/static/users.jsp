<table ng-if="users!=null">
	<tr>
		<td>First Name</td>
		<td>Last Name</td>
		<td>Age</td>
		<td>Address</td>
		<td>Email</td>
		<td>ContNo</td>
		<td>Description</td>
		<td>Role</td>
		<td>delete</td>
		<td>update</td>
	</tr>
	<tr ng-repeat="x in users">
		<td>{{x.fName}}</td>
		<td>{{x.lName}}</td>
		<td>{{x.age}}</td>
		<td>{{x.address}}</td>
		<td>{{x.email}}</td>
		<td>{{x.contNo}}</td>
		<td>{{x.description}}</td>
		<td>{{x.role}}</td>
		<td><button ng-click="deleteUser({{x.userId})">delete</button></td>
		<td><button ng-click="updateUser({{x.userId})">update</button></td>
	</tr>
</table>