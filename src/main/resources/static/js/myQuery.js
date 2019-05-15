
var app= angular.module("myApp",["ngRoute","ui.bootstrap","dialogs"]);
/*app.controller("mainController",function( $scope, $http){	

	$scope.auth = function () {
		console.log('opening pop up');
		var modalInstance = $modal.open({
		templateUrl: 'loginForm.jsp',
		});
	}
	
		
	function addToCart(id){
		alert("add");
		$http({
			method:'GET',
			url:'http://localhost:8080/Indo_weighing/saveToCart'
			}).then(function mySucess(response){
				alert("Product added in cart");
			});
	}
	
});*/


app.config( function($routeProvider) {
	
    $routeProvider
    .when("/", {
    	templateUrl : 'htm/products.htm',
        controller : 'productController'
    })
    .when("/cart", {
    	templateUrl : "htm/products.htm",
        controller : 'cartController'
    })
    .when("/users", {
    	templateUrl : "htm/users.htm",
        controller : "usersController"
    })
    .when('/queryForm', {
    	templateUrl: "src/main/resources/static/users"
        	 
    })
    .when('/deliverdItems', {
    	templateUrl : "htm/products.htm",
        controller: "deliverdItemsController"
        	 
    });
    
}); 

app.controller("productController",function( $scope, $http){
	
		$http({
			method:'GET',
			url:'http://localhost:8080/Indo_weighing/getAllProducts'
			}).then(function mySucess(response){
				$scope.products=response.data;
				$scope.statusCode=response.status;
				console.log($scope.products);
			});
		/*$scope.auth = function () {
			console.log('opening pop up');
			var modalInstance = $modal.open({
			templateUrl: 'loginForm.jsp',
			});
		}
		*/
			
	
	
}); 
app.controller("cartController",function( $scope, $http){
	alert("cartCont");
//	$scope.products.clear();
	
	$http({
		method:'GET',
		url:'http://localhost:8080/Indo_weighing/getCartProduct'
		}).then(function mySucess(response){
			/*if(response.status==401){
				alert("Please login first");
			}else{*/
				$scope.products=response.data;
				$scope.statusCode=response.status;
				$scope.msg="Items in your cart";
				console.log($scope.products);
			
			
			
		});

});
app.controller("usersController",function( $scope, $http,$window){
	console.log(sessionStorage.getItem("token2")+"token without window");
	console.log($window.sessionStorage.getItem("token")+"token window");
	console.log(sessionStorage.token+"only session");
	$scope.token=sessionStorage.getItem("token");
	$http({
		method:'GET',
		url:'http://localhost:8080/Indo_weighing/getAll',
		headers: {
            "Content-type": "application/json",
            "Authorization":"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJrQGdtYWlsLmNvbSIsInBhc3N3b3JkIjoiMTIzNDUiLCJjcmVhdGVkIjoxNTUyOTc0ODY3Nzg2LCJleHAiOjE1NTM1Nzk2Njd9.C1US1JuetD1aAM-7lJX8ths7OCU3Klrm1B27TZUfYGoLPfKYUyvgLQZUUkLQriCJv-fzyyT1ezzFqg8bUBGPAQ"
        }
		}).then(function mySuccess(response) {
	        $scope.users = response.data;
	    }, function myError(response) {
	       $scope.alert="Please Login first";
	        $scope.auth();
	    });
});

app.controller("queryFormController",function( $scope, $http){
	
	$http({
		method:'GET',
		url:'http://localhost:8080/Indo_weighing/getAll'
		}).then(function mySucess(response){
			$scope.products=response.data;
			$scope.statusCode=response.status;
		});

});

app.controller("deliverdItemsController",function( $scope, $http){
	
	$http({
		method:'GET',
		url:'http://localhost:8080/Indo_weighing/getDeliveredItemById'
		}).then(function mySucess(response){
			$scope.products=response.data;
			$scope.statusCode=response.status;
			console.log($scope.products);
			
		});

});



$(document).ready(function () {

    $('#sidebarCollapse').on('click', function () {
        $('#sidebar').toggleClass('active');
    });
    
    

});   
app.config(['$qProvider', function ($qProvider) {
    $qProvider.errorOnUnhandledRejections(false);
}]);



app.controller("mainController", ['$scope', '$modal', '$log','$dialogs',
    function ($scope, $modal, $log, $dialogs) {

        $scope.auth = function () {
        	 $scope.userForm = {
        		        email: '',
        		        username:null,
        		        password: null,
        		        fname:null,
        		        lName:null,
        		        address:null,
        		        contNo:null
        		    };
            console.log($scope.message);

            var modalInstance = $modal.open({
                templateUrl: 'htm/loginForm.htm',
                controller: LoginCtrl,
                scope: $scope,
                resolve: {
                    userForm: function () {
                        return $scope.userForm;
                    }
                }
            });

            modalInstance.result.then(function (selectedItem) {
                $scope.selected = selectedItem;
            }, function () {
                $log.info('Modal dismissed at: ' + new Date());
            });
        };
        
        $scope.register = function () {
        
            
            var modalInstance = $modal.open({
                templateUrl: 'htm/regForm.htm',
                controller: RegistrationCtrl,
                scope: $scope,
                resolve: {
                    userForm: function () {
                        return $scope.user;
                    }
                }
            });

            modalInstance.result.then(function (selectedItem) {
                $scope.selected = selectedItem;
            }, function () {
                $log.info('Modal dismissed at: ' + new Date());
            });
        };
  }]);

var RegistrationCtrl = function ($scope, $modalInstance, $http,userForm,$dialogs) {
    $scope.form = {}
    $scope.userForm=userForm;
    $scope.submitForm = function (user) {
        if ($scope.form.userForm.$valid) {
        	
        	$http({
        		method:'POST',
        		url:'http://localhost:8080/Indo_weighing/save',
        		headers: {
                    "Content-type": "application/json"
                }
                , data: user
        		}).then(function(response) {
        			$scope.alert=response.data.message;
        			if(response.data.status!=1){
        				$scope.register();
        				$dialogs.error($scope.alert);
        			}else{
        				$scope.auth();
        				$dialogs.confirm($scope.alert);
        			}
        			
        	    });
            $modalInstance.close('closed');
        } else {
            console.log('userform is not in scope');
        }
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
};

var LoginCtrl = function ($scope, $modalInstance,$modal, $http,userForm,$dialogs) {
    $scope.form = {}
    $scope.userForm=userForm;
    $scope.submitForm = function (user) {
        if ($scope.form.userForm.$valid) {
        	$http({
        		method:'POST',
        		url:'http://localhost:8080/Indo_weighing/auth',
        		headers: {
                    "Content-type": "application/json"
                }
                , data: user
        		}).then(function mySuccess(response) {
        			
        			$scope.alert=response.data.message;
        			/*sessionStorage.token= response.data.token;
        			$window.sessionStorage.setItem("token",response.data.token);
        			sessionStorage.setItem("token2",response.data.token);
        			console.log(response.data.status);*/
        			if(response.data.status==1){
        				window.alert($scope.alert);
        				$dialogs.confirm($scope.alert);
        			}else{
        				$scope.auth();
        				$dialogs.error($scope.alert);
        				
        			}
        				
        	    }, function myError(response) {
        	        alert("Something went wrong please try again");
        	        $scope.auth();
        	    });
            $modalInstance.close('closed');
        } else {
            console.log('userform is not in scope');
        }
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
}; 