angular.module('auctionhsa.commons')
	.factory('popupService', ['$mdDialog', function($mdDialog, $scope) {
	
	//Se hace un controlador generico, todos los parametros del form que se quieran enviar, se hace a través de object (JSON)
	//Todos los dialogs que involucren formularios deben hacerse a través de este Controller, hide envia como parametro 
	//el JSON al promise que se llama desde el controlador
	function DialogController($scope, $mdDialog) {
		
	    $scope.hide = function() {
	      $mdDialog.hide();
	    };

	    $scope.cancel = function() {
	      $mdDialog.cancel();
	    };

	    $scope.answer = function() {
	    	$mdDialog.hide($scope.object);
	    };
	}

	
	return {
		showAlert : function(title, content) {
			$mdDialog.show( 
					$mdDialog.alert().parent(angular.element(document.querySelector('#popupContainer')))
					.clickOutsideToClose(true)
					.title(title)
					.textContent(content)
					.ariaLabel('Alert Dialog Demo')
					.theme('altTheme')
					.ok('Got it!')
					
			    );
		},
		
		showConfirm : function(title, content) {
			// Appending dialog to document.body to cover sidenav in docs app
			var confirm = $mdDialog.confirm()
			      .title(title)
			      .textContent(content)
			      .ariaLabel('Lucky day')
			      .theme('altTheme')
			      .ok('Yes')
			      .cancel('No');
			return $mdDialog.show(confirm);
		},
		
		showFormDialog : function(cform) {
		    return $mdDialog.show({
		      controller : DialogController,
		      templateUrl: cform,
		      parent: angular.element(document.body),
		      clickOutsideToClose:true
		    });
		}
		
	};
	
	
}])