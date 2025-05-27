import { createRouter, createWebHistory } from 'vue-router'
import EmotionalAnalysisView from '../views/EmotionalAnalysis.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'emotionalAnalysis',
      component: EmotionalAnalysisView
    }
  ],
})

export default router
