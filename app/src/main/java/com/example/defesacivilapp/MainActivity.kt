package com.example.defesacivilapp

import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.defesacivilapp.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        
        // Configurar o drawer
        binding.toolbar.setNavigationOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        
        navView.setNavigationItemSelectedListener(this)

        // Configurar os botões principais
        setupMainButtons()
        
        // Configurar o card do mapa
        setupMapCard()
        
        // Configurar bottom navigation
        setupBottomNavigation()
    }

    private fun setupMainButtons() {
        // Botão Informação
        binding.btnInformacao.setOnClickListener {
            showSnackbar("Informação - Acessando informações sobre emergências")
        }

        // Botão Comunicação
        binding.btnComunicacao.setOnClickListener {
            showSnackbar("Comunicação - Abrindo canais de comunicação")
        }

        // Botão Orientação
        binding.btnOrientacao.setOnClickListener {
            showSnackbar("Orientação - Acessando guias e orientações")
        }

        // Botão Apoio
        binding.btnApoio.setOnClickListener {
            showSnackbar("Apoio - Acessando recursos de apoio")
        }

        // Botão SOS
        binding.btnSos.setOnClickListener {
            showSnackbar("SOS - ATIVAÇÃO DE EMERGÊNCIA!")
            activateEmergencyMode()
        }

        // Botão Participação
        binding.btnParticipacao.setOnClickListener {
            showSnackbar("Participação - Acessando formas de participar")
        }
    }

    private fun setupMapCard() {
        binding.mapCard.setOnClickListener {
            showSnackbar("Mapa - Abrindo mapa de áreas de risco e abrigos")
        }
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> {
                    showSnackbar("Início")
                    true
                }
                R.id.navigation_chat -> {
                    showSnackbar("Chat")
                    true
                }
                R.id.navigation_documents -> {
                    showSnackbar("Documentos")
                    true
                }
                R.id.navigation_folder -> {
                    showSnackbar("Arquivos")
                    true
                }
                R.id.navigation_profile -> {
                    showSnackbar("Perfil")
                    true
                }
                else -> false
            }
        }
    }

    private fun activateEmergencyMode() {
        Toast.makeText(this, "MODO DE EMERGÊNCIA ATIVADO!", Toast.LENGTH_LONG).show()
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onNavigationItemSelected(item: android.view.MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                showSnackbar("Início")
            }
            R.id.nav_emergency -> {
                showSnackbar("Emergências")
            }
            R.id.nav_shelters -> {
                showSnackbar("Abrigos")
            }
            R.id.nav_news -> {
                showSnackbar("Notícias")
            }
            R.id.nav_contact -> {
                showSnackbar("Contato")
            }
            R.id.nav_settings -> {
                showSnackbar("Configurações")
            }
            R.id.nav_about -> {
                showSnackbar("Sobre")
            }
        }

        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
