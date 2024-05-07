<template>
  <div class="container mt-3">
    <h2 class="text-center">Login</h2>
    <form @submit.prevent="login" class="form-signin">
      <div class="form-group">
        <label for="inputEmail" class="sr-only">Email:</label>
        <input type="email" id="inputEmail" v-model="email" class="form-control" placeholder="Email address" required autofocus>
      </div>

      <div class="form-group">
        <label for="inputPassword" class="sr-only">Password:</label>
        <input type="password" id="inputPassword" v-model="password" class="form-control" placeholder="Password" required>
      </div>

      <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
    </form>
    <p v-if="error" class="alert alert-danger">{{ error }}</p>
    <p v-if="message" class="alert alert-success">{{ message }}</p>
  </div>
</template>

<script>
import { ref } from 'vue'
import axios from 'axios'

export default {
  setup() {
    const email = ref('')
    const password = ref('')
    const error = ref(null)
    const message = ref(null) // Add this line

    const login = async () => {
      try {
        const response = await axios.post('http://localhost:8080/user/login', {
          email: email.value,
          password: password.value
        })

        if (response.status === 200) {
          localStorage.setItem('token', response.data.token)
          message.value = 'Registered, if you\'re an admin, you can see the user list' // Modify this line
        } else {
          error.value = 'Invalid email or password'
        }
      } catch (err) {
        console.error(err);
        error.value = 'An error occurred'
      }
    }

    return {
      email,
      password,
      error,
      login,
      message // Add this line
    }
  }
}
</script>