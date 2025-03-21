package br.com.geotry.lifexp.screens.view.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TelaSelecaoViewModel : ViewModel() {
    private val _selectedCharacter = MutableLiveData<Int>()
    val selectedCharacterState: LiveData<Int> = _selectedCharacter

    private val _nickname = MutableLiveData<String>()
    val nicknameState: LiveData<String> = _nickname

    private val _nicknameError = MutableLiveData<Boolean>()
    val nicknameErrorState: LiveData<Boolean> = _nicknameError


    fun setSelectedCharacter(character: Int) {
        _selectedCharacter.value = character
    }

    fun setNickname(novoNickname: String) {
        _nickname.value = novoNickname
    }

    fun setNicknameError(status: Boolean) {
        _nicknameError.value = status
    }
}
