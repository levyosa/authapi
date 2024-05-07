<template>
  <div class="container mt-3">
    <h2 class="text-center">User List</h2>
    <p v-if="errorMessage" class="alert alert-danger">{{ errorMessage }}</p>
    <div v-else class="row">
      <div v-for="user in users" :key="user.id" class="col-md-4">
        <div class="card mb-4">
          <div class="card-body">
            <h5 class="card-title">{{ user.email }}</h5>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  data() {
    return {
      users: [],
      errorMessage: null
    }
  },
  async created() {
    try {
      const response = await axios.get('http://localhost:8080/user', {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`
        }
      })
      this.users = response.data.content
    } catch (error) {
      if (error.response && error.response.status === 403) {
        this.errorMessage = 'Sorry, You\'re unauthorized to see this'
      } else {
        this.errorMessage = 'An error occurred'
      }
    }
  }
}
</script>