<template>
  <div class="container mt-3">
    <h2 class="text-center">Register</h2>
    <form @submit.prevent="register" class="form-signin">
      <div class="form-group">
        <label for="inputEmail" class="sr-only">Email:</label>
        <input type="email" id="inputEmail" v-model="email" class="form-control" placeholder="Email address" required autofocus>
      </div>

      <div class="form-group">
        <label for="inputPassword" class="sr-only">Password:</label>
        <input type="password" id="inputPassword" v-model="password" class="form-control" placeholder="Password" required>
      </div>

      <div class="form-group form-check">
        <input type="checkbox" class="form-check-input" id="checkAdmin" v-model="isAdmin">
        <label class="form-check-label" for="checkAdmin">Register as Admin</label>
      </div>

      <button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
    </form>
    <p v-if="infoMessage" class="alert alert-info">{{ infoMessage }}</p>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  data() {
    return {
      email: '',
      password: '',
      isAdmin: true,
      infoMessage: null
    }
  },
  methods: {
    async register() {
      const role = this.isAdmin ? 'ADMIN' : 'USER'
      try {
        await axios.post('http://localhost:8080/user/register', {
          email: this.email,
          password: this.password,
          role: role
        })
        this.infoMessage = 'Registration successful!'
        setTimeout(() => {
          this.infoMessage = null
        }, 1000)
      } catch (error) {
        if (error.response && error.response.data) {
          this.infoMessage = error.response.data;
          setTimeout(() => {
            this.infoMessage = null
          }, 1000)
        } else {
          console.error(error)
        }
      }
    }
  }
}
</script>